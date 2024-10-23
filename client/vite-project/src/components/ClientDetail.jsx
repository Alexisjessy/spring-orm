import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, Link, useNavigate } from 'react-router-dom';
import apiClient from './apiClient';
function ClientDetails() {
  const { clientId } = useParams(); 
  const [clientDetails, setClientDetails] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState('');
  const [name, setName] = useState('');  
  const navigate = useNavigate();

  useEffect(() => {
    const fetchClientDetails = async () => {
      try {
        const response = await axios.get(`http://localhost:8000/api/clients/${clientId}/details`);
        setClientDetails(response.data);
        setLoading(false);
      } catch (err) {
        setError('Error fetching client details');
        setLoading(false);
      }
    };

    fetchClientDetails();
  }, [clientId]);

  if (loading) {
    return <div className="text-center text-xl font-semibold">Loading client details...</div>;
  }

  if (error) {
    return <div className="text-center text-red-500 text-xl">{error}</div>;
  }

  // Fonction pour ajouter un compte
  const handleSubmit = async () => {
    try {
      const response = await axios.post('http://localhost:8000/api/accounts', {
        balance: 0,
        client: {
          id: clientId,  
        },
      });

      if (response.status === 201) {
        setSuccessMessage('Ajout de compte client réussi ! Redirection vers la page liste client...');
        setTimeout(() => {
          navigate('/client');
        }, 2000);
      }
      console.log('Compte ajouté :', response.data);
    } catch (error) {
      console.error('Erreur lors de l’ajout de compte :', error);
      if (error.response && error.response.data && error.response.data.message) {
        setError(error.response.data.message);
      } else {
        setError("Une erreur s'est produite. Veuillez réessayer.");
      }
    }
  };

  // Fonction pour ajouter une assurance
  const addInsurance = async (e) => {
    e.preventDefault(); 
    try {
     
      const response = await axios.post(`http://localhost:8000/api/clients/${clientId}/insurances`, {
        name: name,
        
      });
      apiClient.interceptors.request.use(config => {
        const token = localStorage.getItem('Token');
        if (token) {
          config.headers['Authorization'] = `Bearer ${token}`;
         
        }
        return config;
      }, (error) => {
        console.error('Erreur lors de l\'envoi de la requête:', error);
        return Promise.reject(error);
      });
      if (response.status === 201) {
        setSuccessMessage('Ajout d\'assurance réussi !');
        setTimeout(() => {
          navigate('/client');
        }, 2000);
      }
      console.log('Assurance ajoutée :', response.data);
    } catch (error) {
      console.error('Erreur lors de l’ajout d’assurance :', error);
      if (error.response && error.response.data && error.response.data.message) {
        setError(error.response.data.message);
      } else {
        setError("Une erreur s'est produite. Veuillez réessayer.");
      }
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="w-full max-w-4xl bg-white shadow-md rounded-lg p-6">
        {successMessage && (
          <div className="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4">
            {successMessage}
          </div>
        )}
        {error && (
          <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4">
            {error}
          </div>
        )}

        <button
          type="submit"
          className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
          onClick={handleSubmit}
        >
          Add Account
        </button>

        <form onSubmit={addInsurance} className="space-y-4 mt-6">
          <div>
            <label className="block text-sm font-medium text-gray-700">ADD INSURANCE</label>
            <select
              value={name} 
              onChange={(e) => setName(e.target.value)} 
              className="block w-full p-2 border border-gray-300 rounded-md"
              required
            >
              <option value="">-- Choisir une assurance --</option>
              <option value="HABITATION">HABITATION</option>
              <option value="SANTE">SANTE</option>
              <option value="VIE">VIE</option>
              <option value="AUTOMOBILE">AUTOMOBILE</option>
              <option value="SCOLAIRE">SCOLAIRE</option>
              <option value="RESPONSABILITE_CIVILE_PERSONNELLE">RESPONSABILITE_CIVILE_PERSONNELLE</option>
            </select>
          </div>
          <button
            type="submit"
            className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
          >
            Add Insurance
          </button>
        </form>

        <h1 className="text-3xl font-semibold text-center text-indigo-600 mb-6">Client Details</h1>

        {clientDetails && (
          <>
            <h2 className="text-xl font-semibold mb-4">Client Information</h2>
            <p><strong>Name:</strong> {clientDetails.firstName} {clientDetails.lastName}</p>
            <p><strong>Email:</strong> {clientDetails.email}</p>
            <p><strong>Birthday:</strong> {new Date(clientDetails.birthday).toLocaleDateString()}</p>

            <h2 className="text-xl font-semibold mt-6 mb-4">Accounts</h2>
            {clientDetails.accounts.length > 0 ? (
              <table className="min-w-full bg-white border border-gray-200 rounded-lg mb-6">
                <thead>
                  <tr className="bg-gray-50">
                    <th className="py-2 px-4 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-700">Account ID</th>
                    <th className="py-2 px-4 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-700">Balance</th>
                    <th className="py-2 px-4 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-700">Creation Time</th>
                  </tr>
                </thead>
                <tbody>
                  {clientDetails.accounts.map((account) => (
                    <tr key={account.id} className="border-t">
                      <td className="py-2 px-4 border-b border-gray-200 text-sm text-gray-900">{account.id}</td>
                      <td className="py-2 px-4 border-b border-gray-200 text-sm text-gray-900">{account.balance} €</td>
                      <td className="py-2 px-4 border-b border-gray-200 text-sm text-gray-900">{new Date(account.creationTime).toLocaleString()}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
              <p>No accounts found for this client.</p>
            )}

            <h2 className="text-xl font-semibold mt-6 mb-4">Insurances</h2>
            {clientDetails.insurances.length > 0 ? (
              <ul className="list-disc pl-5">
                {clientDetails.insurances.map((insurance) => (
                  <li key={insurance.id} className="text-sm text-gray-900">{insurance.name}</li>
                  
                ))}
              </ul>
            ) : (
              <p>No insurances found for this client.</p>
            )}
          </>
        )}
      </div>
    </div>
  );
}

export default ClientDetails;
