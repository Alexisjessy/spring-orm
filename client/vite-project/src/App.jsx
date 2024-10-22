import React, { useContext,useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes,Link } from 'react-router-dom';
import AddClient from './components/AddClient';
import ClientList from './components/ClientList';
import SingnIn from './components/SignIn';
import Login from './components/Login';
import ClientBankAccounts from './components/ClientBankAccounts';
import ClientDetail from './components/ClientDetail';

import { AuthContext } from './components/AuthContext';
import LogoutButton from './components/LogoutButton'; 
function Home() {
  return (
    <div className="text-center mt-10">
      <h1 className="text-3xl font-bold">Welcome to Client Management</h1>

      <img src="https://i.kinja-img.com/gawker-media/image/upload/s--1IbYcQm2--/c_fill,fl_progressive,g_center,h_900,q_80,w_1600/zj42kn2xzlivgllfpa2u.jpg"/> 
    </div>
  );
}

function App() {
  const { user, loading ,logout,login } = useContext(AuthContext); 
  useEffect(() => {
    console.log('L\'utilisateur a changé:', user);
  }, [user]);
  if (loading) {
    return <div>Chargement...</div>; 
  }

  return (
    <Router>
        <div className="container mx-auto mt-5">
      <nav className="flex justify-between items-center p-5 bg-gray-100 shadow-md rounded-lg mb-4">
  <div className="flex space-x-4">
    <Link to="/" className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600">
     Home
    </Link>
    <Link to="/add-client" className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600">
        Add New Client
      </Link>
      <Link to="/client" className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600">
         Client
      </Link>
      <Link to="/accounts" className="px-4 py-2 bg-purple-500 text-white rounded-md hover:bg-purple-600">
         comptes bancaires
      </Link>

      <div className="flex space-x-4">
    {user ? ( 
      <LogoutButton />
    ) : ( 
      <>
        <Link to="/auth" className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600">
       Register
      </Link>
      <Link to="/auth/login" className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600">
       Login
      </Link>
      </>
    )}
  </div>
      
    </div>
    
          </nav>
       
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/add-client" element={<AddClient />} />
          <Route path="/accounts" element={<ClientBankAccounts />} />
          <Route path="/client" element={<ClientList />} />
          <Route path="/auth" element={<SingnIn />} />
          <Route path="/auth/login" element={<Login/>} />
          <Route path="/clients/:clientId/details" element={<ClientDetail/>}/>
          
        </Routes>
      </div>
      
    </Router>
  );
}

export default App;
