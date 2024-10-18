import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom'; 

function ClientBankAccounts() {
  const { clientId } = useParams(); 
  const [accounts, setAccounts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchBankAccounts = async () => {
      try {
        const response = await axios.get(`http://localhost:8000/api/accounts`);
        setAccounts(response.data);
        setLoading(false);
      } catch (err) {
        setError('Error fetching bank accounts');
        setLoading(false);
      }
    };

    fetchBankAccounts();
  }, [clientId]);

  if (loading) {
    return <div className="text-center text-xl font-semibold">Loading bank accounts...</div>;
  }

  if (error) {
    return <div className="text-center text-red-500 text-xl">{error}</div>;
  }

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="w-full max-w-4xl bg-white shadow-md rounded-lg p-6">
        <h1 className="text-3xl font-semibold text-center text-indigo-600 mb-6">
          Bank Accounts for Client {clientId}
        </h1>
        <table className="min-w-full bg-white border border-gray-200 rounded-lg">
          <thead>
            <tr className="bg-gray-50">
              <th className="py-2 px-4 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-700">
                Account Number (Client ID)
              </th>
              <th className="py-2 px-4 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-700">
                Balance
              </th>
              <th className="py-2 px-4 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-700">
                Creation Time
              </th>
            </tr>
          </thead>
          <tbody>
            {accounts.length > 0 ? (
              accounts.map((account) => (
                <tr key={account.id} className="border-t">
                  <td className="py-2 px-4 border-b border-gray-200 text-sm text-gray-900">
                    {account.clientId} 
                  </td>
                  <td className="py-2 px-4 border-b border-gray-200 text-sm text-gray-900">
                    {account.balance} €
                  </td>
                  <td className="py-2 px-4 border-b border-gray-200 text-sm text-gray-900">
                    {new Date(account.creationTime).toLocaleString()}
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="3" className="py-4 px-4 text-center text-gray-500">
                  No bank accounts found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ClientBankAccounts;
