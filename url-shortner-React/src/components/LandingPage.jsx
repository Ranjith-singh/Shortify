import React from 'react'
import Card from './Card';
import {motion} from 'framer-motion'
import { useNavigate } from "react-router-dom";
import { useStoreContext } from '../contextApi/ContextApi';

let desc =
  "Generate short, memorable links with ease using Shortify’s intuitive interface. Share URLs effortlessly across platforms. Optimize your sharing strategy with Shortify. Track clicks and manage your links seamlessly to enhance your online presence. Generate short, memorable links with ease using Shortify’s intuitive interface. Share URLs effortlessly across platforms.";


function LandingPage() {
  const navigate = useNavigate();
  const {token} = useStoreContext();
  // console.log("AccessToken",token);
  const dashBoardNavigateHandler = () => {
      token? navigate("/dashboard"): navigate("/login")
  };
  return (
    <div className="min-h-[calc(100vh-64px)]  lg:px-14 sm:px-8 px-4">
      <div className="lg:flex-row flex-col    lg:py-5   pt-16   lg:gap-10 gap-8 flex justify-between items-center">
        <div className=" flex-1">
            <motion.h1
                initial = {{opacity : 0, y : -80}}
                whileInView={{
                  opacity : 1,
                  y : 0
                }} 
                viewport={{once : true}}
                transition={{duration : 1.0}}
                className="font-bold font-roboto text-slate-800 md:text-5xl sm:text-4xl text-3xl   md:leading-[55px] sm:leading-[45px] leading-10 lg:w-full md:w-[70%] w-full">
                Shortify Simplifies URL Shortening For Efficient Sharing.
            </motion.h1>
            <motion.p
                initial = {{opacity : 0, y : -80}}
                whileInView={{
                  opacity : 1,
                  y : 0
                }}
                viewport={{once : true}}
                transition={{duration : 1.0}}
                className="text-slate-700 text-sm my-5">
                Shortify streamlines the process of URL shortening, making sharing
                links effortless and efficient. With its user-friendly interface,
                Shortify allows you to generate concise, easy-to-share URLs in
                seconds. Simplify your sharing experience with Shortify today.
            </motion.p>
            <div className="flex items-center gap-3">
            <motion.button
              initial={{ opacity: 0, y: 80 }}
              whileInView={{
                opacity: 1,
                y: 0,
              }}
              viewport={{ once: true }}
              transition={{ duration: 0.8 }}
              onClick={dashBoardNavigateHandler}
              className="hover:bg-custom-gradient hover:text-white border-btnColor border text-btnColor rounded-xl  py-2 w-40"
            >
              Manage Links
            </motion.button>
            <motion.button
              initial={{ opacity: 0, y: 80 }}
              whileInView={{
                opacity: 1,
                y: 0,
              }}
              viewport={{ once: true }}
              transition={{ duration: 0.8 }}
              onClick={dashBoardNavigateHandler}
              className="hover:bg-custom-gradient hover:text-white border-btnColor border text-btnColor rounded-xl  py-2 w-40"
            >
              Create Short Link
            </motion.button>
          </div>
        </div>
        {/* <div className="   flex-1 flex   justify-center w-full">
          <motion.img
            initial={{ opacity: 0 }}
            whileInView={{
              opacity: 1,
            }}
            whileHover={{ rotate:720, opacity:0}}
            viewport={{ once: true }}
            transition={{ duration: 1.5 }}
            className="w-full h-full object-cover transition-opacity duration-1000 opacity-100 group-hover:opacity-0"
            src="/images/img2.png"
            alt=""
          />
        </div> */}
        <div className="flex-1 flex justify-center w-full">
          <motion.div
            initial="initial"
            whileHover="hover"
            className="relative w-full h-full max-w-[450px] max-h-[450px]" // adjust size as needed
          >
            <motion.img
              src="/images/img2.png"
              alt="Default"
              className="relative top-0 left-0 w-full h-full object-cover"
              variants={{
                initial: { opacity: 1 },
                hover: { rotate:1080,opacity: 0 },
              }}
              transition={{ duration: 1.5 }}
            />
            <motion.img
              src="/images/Mangekyou_Sharingan_Kakashi.png"
              alt="Hover"
              className="absolute top-0 left-0 w-full h-full object-cover"
              variants={{
                initial: { opacity: 0 },
                hover: { rotate:1080, opacity: 1 },
              }}
              transition={{ duration: 1.5 }}
            />
          </motion.div>
        </div>
      </div>
      <div className="sm:pt-12 pt-3.5">
        <motion.p
          initial={{ opacity: 0, y: 50 }}
          whileInView={{
            opacity: 1,
            y: 0,
          }}
          viewport={{ once: true }}
          transition={{ duration: 0.8 }}
          className="text-slate-800 font-roboto font-bold lg:w-[60%]  md:w-[70%] sm:w-[80%] mx-auto text-3xl text-center"
        >
          Trusted by individuals and teams at the world best companies{" "}
        </motion.p>
        <p className="text-slate-800 font-roboto font-bold lg:w-[60%]  md:w-[70%] sm:w-[80%] mx-auto text-3xl text-center pt-10">
          how it works
        </p>
        <div className="pt-4 pb-7 grid lg:gap-7 gap-4 xl:grid-cols-4  lg:grid-cols-3 sm:grid-cols-2 grid-cols-1 mt-4">
          <Card
            title="Signup/SignIn"
            desc="Signup using username, email & password, then Signin using email & password to get the AccessToken for accessing different urls"
          />
          <Card
            title="Create a Short Url"
            desc="Provide the original link to any website and then click on generate url to get the short url, which gets copied to the clipboard"
          />
          <Card
            title="Url Re-routing"
            desc="When you click on the generated short url, you will be redirected to the original url while keeping the click count for analysis"
          />
          <Card
            title="Analysis"
            desc="Based on the click count you can get the clicks per day for particular url as well as for all short urls owned by the User"
          />
        </div>
      </div>
    </div>
  )
}

export default LandingPage
