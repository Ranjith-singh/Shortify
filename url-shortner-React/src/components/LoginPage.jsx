import React, { useState } from 'react'
import { useForm } from 'react-hook-form'
import TextField from './TextField';
import { Link, useNavigate } from 'react-router-dom';
import api from '../api/api';
import toast from 'react-hot-toast';
import { useStoreContext } from '../contextApi/ContextApi';

const LoginPage = () => {
    const navigate = useNavigate();
    const [loader, setLoader] = useState(false);
    const { setToken } = useStoreContext();

    const {
        register,
        handleSubmit,
        reset,
        formState: {errors}
    } = useForm({
        defaultValues: {
            email: "",
            password: ""
        },
        mode: "onTouched",
    });

    const onLogOutHandler = () => {
        localStorage.removeItem("AccessToken");
        setToken(false);
        navigate("/login");
    };

    const loginHandler = async(data) =>{
        setLoader(true);
        try {
            console.log(data);
            const { data: response } = await api.post(
                "/api/auth/public/login",
                data
            );
            // console.log("AccessToken",response.token);
            setToken(response.token);
            const logoutTime = 1000*60*60*24;
            localStorage.setItem('AccessToken',JSON.stringify(response.token));
            setTimeout(()=>{
                onLogOutHandler();
            },logoutTime);

            reset();
            navigate("/dashboard");
            toast.success("Successful Login!")
        } catch (error) {
            // navigate("/error");
            console.log(error);
            toast.error(`Login Failed!\nInvalid email or password`)
        } finally {
            setLoader(false);
        }
    };

    return (
        <div
            className='min-h-[calc(100vh-64px)] flex justify-center items-center'>
            <form onSubmit={handleSubmit(loginHandler)}
                className="sm:w-[450px] w-[360px]  shadow-custom py-8 sm:px-8 px-4 rounded-md">
                <h1 className="text-center font-serif text-btnColor font-bold lg:text-3xl text-2xl">
                    Login Here
                </h1>
    
                <hr className='mt-2 mb-5 text-black'/>
    
                <div className="flex flex-col gap-3">
                    <TextField
                        label="email"
                        required
                        id="email"
                        type="text"
                        message="*email is required"
                        placeholder="Type your email"
                        register={register}
                        errors={errors}
                    />
    
                    <TextField
                        label="Password"
                        required
                        id="password"
                        type="password"
                        message="*Password is required"
                        placeholder="Type your password"
                        register={register}
                        min={6}
                        errors={errors}
                    />
                </div>
    
                <button
                    disabled={loader}
                    type='submit'
                    className='bg-customRed font-semibold text-white  bg-custom-gradient w-full py-2 hover:text-slate-400 transition-colors duration-100 rounded-sm my-3'>
                    {loader ? "Loading..." : "Login"}
                </button>
    
                <p className='text-center text-sm text-slate-700 mt-6'>
                    don't have an account? 
                    <Link
                        className='font-semibold underline hover:text-black'
                        to="/register">
                            <span className='text-btnColor'> Signup</span>
                    </Link>
                </p>
            </form>
        </div>
      )
}

export default LoginPage
