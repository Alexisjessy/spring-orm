import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom'; 

function ClientList() {
  const [clients, setClients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchClients = async () => {
      try {
        const response = await axios.get('http://localhost:8000/api/clients');
        console.log(response.data);
        setClients(response.data);
        setLoading(false);
      } catch (err) {
        setError('Error fetching clients');
        setLoading(false);
      }
    };

    fetchClients();
  }, []);

  if (loading) {
    return <div className="text-center text-xl font-semibold">Loading clients...</div>;
  }

  if (error) {
    return <div className="text-center text-red-500 text-xl">{error}</div>;
  }

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="w-full max-w-4xl bg-white shadow-md rounded-lg p-6">
        <h1 className="text-3xl font-semibold text-center text-indigo-600 mb-6">Clients List</h1>
        <table className="min-w-full bg-white border border-gray-200 rounded-lg">
          <thead>
            <tr className="bg-gray-50">
              <th className="py-2 px-4 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-700">
                First Name
              </th>
              <th className="py-2 px-4 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-700">
                Last Name
              </th>
              <th className="py-2 px-4 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-700">
                Email
              </th>
              <th className="py-2 px-4 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-700">
                Birthday
              </th>
            </tr>
          </thead>
          <tbody>
            {clients.length > 0 ? (
              clients.map((client) => (
                <tr key={client.id} className="border-t cursor-pointer hover:bg-gray-100">
                  <td className="py-2 px-4 border-b border-gray-200 text-sm text-gray-900">
                    <Link to={`/clients/${client.id}/details`} className="block w-full h-full">
                      {client.firstName}
                    </Link>
                  </td>
                  <td className="py-2 px-4 border-b border-gray-200 text-sm text-gray-900">
                    <Link to={`/clients/${client.id}/details`} className="block w-full h-full">
                      {client.lastName}
                    </Link>
                  </td>
                  <td className="py-2 px-4 border-b border-gray-200 text-sm text-gray-900">
                    <Link to={`/clients/${client.id}/details`} className="block w-full h-full">
                      {client.email}
                    </Link>
                  </td>
                  <td className="py-2 px-4 border-b border-gray-200 text-sm text-gray-900">
                    <Link to={`/clients/${client.id}/details`} className="block w-full h-full">
                      {client.birthday}
                    </Link>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="4" className="py-4 px-4 text-center text-gray-500">
                  No clients found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ClientList;
