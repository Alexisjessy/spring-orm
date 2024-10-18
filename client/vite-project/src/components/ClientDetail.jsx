import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom'; 

function ClientDetails() {
  const { clientId } = useParams(); 
  const [clientDetails, setClientDetails] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

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

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="w-full max-w-4xl bg-white shadow-md rounded-lg p-6">
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
