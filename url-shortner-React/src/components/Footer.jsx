import React from "react";
import { FaFacebook, FaTwitter, FaFileAlt, FaGithub, FaLinkedin } from "react-icons/fa";
import { SiGeeksforgeeks} from "react-icons/si"

const Footer = () => {
  return (
    <footer className="bg-custom-gradient text-white py-8 z-40 relative">
      <div className="container mx-auto px-6 lg:px-14 flex flex-col lg:flex-row lg:justify-between items-center gap-4">
        <div className="text-center lg:text-left">
          <h2 className="text-3xl font-bold mb-2">Shortify</h2>
          <p>Simplifying URL shortening for efficient sharing</p>
        </div>

        <p className="mt-4 lg:mt-0">
          &copy; 2025 Shortify. All rights reserved.
        </p>

        <div className="flex space-x-6 mt-4 lg:mt-0">
          <a href="https://github.com/Ranjith-singh" className="hover:text-gray-200">
            <FaGithub size={24} />
          </a>
          <a href="https://www.linkedin.com/in/ranjith-singh-s-061bba251/" className="hover:text-gray-200">
            <FaLinkedin size={24} />
          </a>
          <a href="https://drive.google.com/file/d/1UK8me9Vnjt4xAGIFoT3Nq249Kstq-dsS/view?usp=sharing" className="hover:text-gray-200">
            <FaFileAlt size={24} />
          </a>
          <a href="https://www.geeksforgeeks.org/user/singhranpm5l/" className="hover:text-gray-200">
            <SiGeeksforgeeks size={24} />
          </a>
        </div>
      </div>
    </footer>
  );
};

export default Footer;