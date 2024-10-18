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
      <p className="text-lg mt-4">Navigate to add a new client.</p>
      <Link to="/add-client" className="mt-6 inline-block px-4 py-2 bg-indigo-600 text-white rounded hover:bg-indigo-700">
        Add New Client
      </Link>
      <Link to="/client" className="mt-6 inline-block px-4 py-2 bg-indigo-600 text-white rounded hover:bg-indigo-700">
         Client
      </Link>
      <Link to="/accounts" className="mt-6 inline-block px-4 py-2 bg-indigo-600 text-white rounded hover:bg-indigo-700">
         comptes bancaires
      </Link>
    </div>
  );
}

function App() {
  return (
    <Router>
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
