import React, { useState } from 'react'
import { useForm } from 'react-hook-form'
import TextField from './TextField';
import { Link, useNavigate } from 'react-router-dom';
import api from '../api/api';
import toast from 'react-hot-toast';
import { useStoreContext } from '../contextApi/ContextApi';

const Email = () => {
    const navigate = useNavigate();
    const [loader, setLoader] = useState(false);

    const {
        register,
        handleSubmit,
        reset,
        formState: {errors}
    } = useForm({
        defaultValues: {
            email: ""
        },
        mode: "onTouched",
    });

    const emailHandler = async(data) =>{
        setLoader(true);
        // console.log("email: ",data["email"]);
        const emailData = {
            "senderEmail" : `${import.meta.env.SENDER_EMAIL}`,
            "receiverEmail" : data["email"],
            "subject" : "Reset password Request",
            "body" : `${import.meta.env.VITE_FRONTEND_URL}`
        }
        try {
            // console.log(data);
            await api.post(
                "/api/auth/forgot-password",
                emailData
            );
            reset();
            toast.success("Email sent")
        } catch (error) {
            // navigate("/error");
            console.log(error);
            toast.error(`Email doesn't exists in the database, please Register`);
        }
        finally{
            setLoader(false);
        }
    };

    return (
        <div
            className='min-h-[calc(100vh-64px)] flex justify-center items-center'>
            <form onSubmit={handleSubmit(emailHandler)}
                className="sm:w-[450px] w-[360px]  shadow-custom py-8 sm:px-8 px-4 rounded-md">
                <h1 className="text-center font-serif text-btnColor font-bold lg:text-3xl text-2xl">
                    Enter Email id
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
                </div>
    
                <button
                    disabled={loader}
                    type='submit'
                    className='bg-customRed font-semibold text-white  bg-custom-gradient w-full py-2 hover:text-slate-400 transition-colors duration-100 rounded-sm my-3'>
                    {loader ? "Loading..." : "Send Email"}
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

export default Email
