import React, { useState } from 'react'
import { useForm } from 'react-hook-form'
import TextField from './TextField';
import { useNavigate, useParams, useSearchParams } from 'react-router-dom';
import api from '../api/api';
import toast from 'react-hot-toast';

const ResetPassword = () => {
    const navigate = useNavigate();
    const [loader, setLoader] = useState(false);
    const [searchParams] = useSearchParams();

    const {
        register,
        handleSubmit,
        reset,
        watch,
        formState: {errors}
    } = useForm({
        defaultValues: {
            password: "",
            confirmPassword: ""
        },
        mode: "onTouched",
    });

    const resetPassword = async(data) =>{
        setLoader(true);
        try {
            const tokenPar = searchParams.get("token");
            const changePassword = {
                token : tokenPar,
                password : data["password"]
            }
            await api.get(
                `/api/auth/validate-reset-token?token=${tokenPar}`
            );
            await api.post(
                "/api/auth/reset-password",
                changePassword
            );
            reset();
            navigate("/login");
            toast.success("Password changed successfully!")
        } catch (error) {
            console.log(error);
            navigate("/login");
            toast.error(error.response.data.message);
        } finally {
            setLoader(false);
        }
    };

    return (
        <div
            className='min-h-[calc(100vh-64px)] flex justify-center items-center'>
            <form onSubmit={handleSubmit(resetPassword)}
                className="sm:w-[450px] w-[360px]  shadow-custom py-8 sm:px-8 px-4 rounded-md">
                <h1 className="text-center font-serif text-btnColor font-bold lg:text-3xl text-2xl">
                    Change Password
                </h1>
    
                <hr className='mt-2 mb-5 text-black'/>
    
                <div className="flex flex-col gap-3">
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
    
                    <TextField
                        label="ConfirmPassword"
                        required
                        id="confirmPassword"
                        type="password"
                        message="*ConfirmPassword is required"
                        placeholder="Confirm your password"
                        register={register}
                        min={6}
                        validate={(value) => (value === watch("password") || "Passwords do not match")}
                        errors={errors}
                    />
                </div>
    
                <button
                    disabled={loader}
                    type='submit'
                    className='bg-customRed font-semibold text-white  bg-custom-gradient w-full py-2 hover:text-slate-400 transition-colors duration-100 rounded-sm my-3'>
                    {loader ? "Loading..." : "Change Password"}
                </button>
            </form>
        </div>
    )
}

export default ResetPassword
