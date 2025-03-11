import { Navigate } from 'react-router-dom';
import { useStoreContext } from './contextApi/ContextApi'

const PrivateRoute = ({children, publicPage}) => {
    const {token} = useStoreContext();

    if (publicPage) {
        return token ? <Navigate to="/dashboard" /> : children;
    }

    return !token ? <Navigate to="/login" /> : children;
}

export default PrivateRoute
