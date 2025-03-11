import React from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import LandingPage from './components/LandingPage'
import AboutPage from './components/AboutPage'
import NavBar from './components/NavBar'
import Footer from './components/Footer'
import RegisterPage from './components/RegisterPage'
import { Toaster } from 'react-hot-toast'
import LoginPage from './components/LoginPage'
import DashboardLayout from './components/dashboard/DashboardLayout'
import ShortenUrlPage from './components/ShortenUrlPage'

const AppRouter = () => {
  return (
    <>
        <NavBar/>
        <Toaster/>
        <Routes>
          <Route path='/' element={<LandingPage/>}/>
          <Route path='/about' element={<AboutPage/>}/>
          <Route path='/register' element={<RegisterPage/>}/>
          <Route path='/login' element={<LoginPage/>}/>
          <Route path='/dashboard' element={<DashboardLayout/>}/>
        </Routes>
        <Footer/>
    </>
  )
};

export default AppRouter;

export const SubDomainRouter = () => {
  return (
    <Routes>
      <Route path='/:url' element={<ShortenUrlPage/>}/>
    </Routes>
  )
};
