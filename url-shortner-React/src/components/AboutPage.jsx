import React from "react";
import { FaLink, FaShareAlt, FaEdit, FaChartLine } from "react-icons/fa";
import {color, hover, motion} from "framer-motion"

const AboutPage = () => {
  return (
    <div className="lg:px-14 sm:px-8 px-5 min-h-[calc(100vh-64px)] pt-2">
      <div className="bg-white w-full sm:py-10 py-8  ">
        <h1 className="sm:text-4xl text-slate-800 text-3xl font-bold italic  mb-3">
          About Shortify
        </h1>
        <p className="text-gray-700 text-sm  mb-8 xl:w-[60%] lg:w-[70%] sm:w-[80%] w-full ">
          Shortify simplifies URL shortening for efficient sharing. Easily
          generate, manage, and track your shortened links.
        </p>
        <div className="space-y-5 xl:w-[60%] lg:w-[70%] sm:w-[80%] w-full ">
          <motion.div
            whileHover={{scale: 1.1}}
            whileTap={{scale: 0.9}}
            className="flex items-start">
            <FaLink className="text-blue-500 text-3xl mr-4" />
            <motion.div whileHover= "hover"
            >
              <motion.h2 variants = {{hover:{color:"#2754F5"}}}
              className="sm:text-2xl font-bold text-slate-800">
                Simple URL Shortening
              </motion.h2>
              <p className="text-gray-600">
                Experience the ease of creating short, memorable URLs in just a
                few clicks. Our intuitive interface and quick setup process
                ensure you can start shortening URLs without any hassle.
              </p>
            </motion.div>
          </motion.div>
          <motion.div
            whileHover={{scale: 1.1}}
            whileTap={{scale: 0.9}}
            className="flex items-start">
            <FaShareAlt className="text-green-500 text-3xl mr-4" />
            <motion.div whileHover= "hover"
            >
              <motion.h2 variants = {{hover:{color:"#4caf50"}}}
                className="sm:text-2xl font-bold text-slate-800">
                Powerful Analytics
              </motion.h2>
              <p className="text-gray-600">
                Gain insights into your link performance with our comprehensive
                analytics dashboard. Track clicks, geographical data, and
                referral sources to optimize your marketing strategies.
              </p>
            </motion.div>
          </motion.div>
          <motion.div
              whileHover={{scale: 1.1}}
              whileTap={{scale: 0.9}}
              className="flex items-start">
            <FaEdit className="text-purple-500 text-3xl mr-4" />
            <motion.div whileHover= "hover"
            >
              <motion.h2 variants = {{hover:{color:"#9C27B0"}}}
                className="sm:text-2xl font-bold text-slate-800">
                Enhanced Security
              </motion.h2>
              <p className="text-gray-600">
                Rest assured with our robust security measures. All shortened
                URLs are protected with advanced encryption, ensuring your data
                remains safe and secure.
              </p>
            </motion.div>
          </motion.div>
          <motion.div
            whileHover={{scale: 1.1}}
            whileTap={{scale: 0.9}}
            className="flex items-start">
            <FaChartLine className="text-red-500 text-3xl mr-4" />
            <motion.div whileHover= "hover"
            >
              <motion.h2 variants = {{hover:{color:"#f44336"}}}
                className="sm:text-2xl font-bold text-slate-800">
                Fast and Reliable
              </motion.h2>
              <p className="text-gray-600">
                Enjoy lightning-fast redirects and high uptime with our reliable
                infrastructure. Your shortened URLs will always be available and
                responsive, ensuring a seamless experience for your users.
              </p>
            </motion.div>
            {/* <div>
              <h2 className="sm:text-2xl font-bold text-slate-800 hover:text-red-500">
                Fast and Reliable
              </h2>
              <p className="text-gray-600">
                Enjoy lightning-fast redirects and high uptime with our reliable
                infrastructure. Your shortened URLs will always be available and
                responsive, ensuring a seamless experience for your users.
              </p>
            </div> */}
          </motion.div>
        </div>
      </div>
    </div>
  );
};

export default AboutPage;