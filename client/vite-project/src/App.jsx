import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AddClient from './components/AddClient';
import ClientList from './components/ClientList';
import ClientBankAccounts from './components/ClientBankAccounts';
import ClientDetail from './components/ClientDetail';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <div className="text-center mt-10">
      <h1 className="text-3xl font-bold">Welcome to Client Management</h1>
      
    </div>
  );
}

function App() {
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
     
    </div>
          </nav>
          </div>
      <div>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/add-client" element={<AddClient />} />
          <Route path="/accounts" element={<ClientBankAccounts />} />
          <Route path="/client" element={<ClientList />} />
          <Route path="/clients/:clientId/details" element={<ClientDetail />} />
          
        </Routes>
      </div>
      
    </Router>
  );
}

export default App;
