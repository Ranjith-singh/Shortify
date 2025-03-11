import React from 'react'
import { InfinitySpin } from 'react-loader-spinner'

const Loader = () => {
  return (
    <div className=" flex justify-center items-center w-full h-fit">
        <div className="flex flex-col items-center gap-1">
        <InfinitySpin
            visible={true}
            width="200"
            color="#000FFF"
            ariaLabel="infinity-spin-loading"
            />
        </div>
    </div>
  )
}

export default Loader
