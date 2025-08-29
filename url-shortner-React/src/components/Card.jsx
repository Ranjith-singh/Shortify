import React from 'react'
import {motion} from 'framer-motion'
import { grey } from '@mui/material/colors'

function Card({title, desc}) {
  return (
    <motion.div
      initial={{ opacity: 0, y: 120 }}
      whileInView={{
        opacity: 1,
        y: 0,
      }}
      whileHover={{ scale: 1.1 , backgroundColor: "#F2F2F2"}}
      whileTap={{ scale: 0.9}}
      transition={{ duration: 1.0 , type: "spring", stiffness: 200}}
      className="shadow-md shadow-slate-400 border flex flex-col px-4 py-8  gap-3 rounded-sm"
    >
      <h1 className="text-slate-900 text-xl font-bold ">{title}</h1>
      <p className="text-slate-700 text-sm"> {desc}</p>
    </motion.div>
  )
}

export default Card
