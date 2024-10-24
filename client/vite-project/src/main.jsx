import React from 'react';
import ReactDOM from 'react-dom/client'; 
import './index.css';
import App from './App';
import { AuthProvider } from './components/AuthContext';  
ReactDOM.createRoot(document.getElementById('root')).render(
<<<<<<< HEAD

     <AuthProvider> 
    <App />
    </AuthProvider> 
 
=======
  // <React.StrictMode>
     <AuthProvider> 
    <App />
    </AuthProvider> 
  // </React.StrictMode>,
>>>>>>> 0f9a292d2045b83a746bf1b2bda385d1385bf3c8
  
);
