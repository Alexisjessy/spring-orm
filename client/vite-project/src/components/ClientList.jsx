import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Modal from 'react-modal';
import apiClient from './apiClient'; 

function ClientList() {
  const [clients, setClients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isConfirmationModalOpen, setConfirmationModalOpen] = useState(false);
  const [selectedClient, setSelectedClient] = useState(null);


  useEffect(() => {
    const fetchClients = async () => {
      try {
        const response = await apiClient.get('/api/clients');  
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

  const handleDelete = (clientId) => {
    setSelectedClient(clientId);
    setConfirmationModalOpen(true);
  };

  const confirmDelete = async () => {
    if (selectedClient) {
      try {
        
        await apiClient.delete(`/clients/${selectedClient}`);
        setClients(clients.filter((client) => client.id !== selectedClient));
        setConfirmationModalOpen(false);
      } catch (error) {
        console.error('Erreur lors de la suppression du client :', error);
      }
    }
  };

  const closeModal = () => {
    setConfirmationModalOpen(false);
    setSelectedClient(null);
  };

  return (
    <section>
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
                <th className="py-2 px-4 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-700">
                  Actions
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
                    <td>
                      <button
                        className="bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded"
                        onClick={() => handleDelete(client.id)}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="5" className="py-4 px-4 text-center text-gray-500">
                    No clients found.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>

      <Modal
        className="w-screen h-screen bg-black bg-opacity-30 fixed top-0 right-0 flex justify-center items-center"
        isOpen={isConfirmationModalOpen}
        onRequestClose={closeModal}
        contentLabel="Confirmation Modal"
      >
        <div className="bg-white p-10 rounded-md shadow-md">
          <h2 className="font-bold text-center text-lg my-5">Confirm Delete Client</h2>
          <button
            className="outline outline-1 outline-[#101f20] bg-[#101f20] text-white py-2 px-4 hover:bg-transparent hover:text-black"
            onClick={confirmDelete}
          >
            Yes
          </button>
          <button
            className="bg-red-500 text-white font-bold py-2 px-4 rounded ml-4 hover:bg-[#b91c1c]"
            onClick={closeModal}
          >
            No
          </button>
        </div>
      </Modal>
    </section>
  );
}

export default ClientList;
