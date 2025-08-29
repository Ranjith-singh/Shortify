import React, { useEffect } from 'react'
import { useParams } from 'react-router-dom'
import Loader from './Loader'

const ShortenUrlPage = () => {
    const { url } = useParams();
    // console.log("url",url);
    useEffect(() => {
        if (url) {
            window.location.href = import.meta.env.VITE_BACKEND_URL + `/${url}`;
        }
    }, [url]);
    return (<Loader></Loader>);
}

export default ShortenUrlPage