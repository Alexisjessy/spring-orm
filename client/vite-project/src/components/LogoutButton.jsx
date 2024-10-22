import React, { useContext } from 'react';
import { AuthContext } from './AuthContext';

const LogoutButton = () => {
  const { logout } = useContext(AuthContext); // Récupérer la fonction logout depuis AuthContext

  const handleLogout = async () => {
    try {
      await logout(); // Utiliser la fonction logout
      alert('Déconnexion réussie !');
    } catch (error) {
      console.error('Erreur lors de la déconnexion', error);
    }
  };

  return (
    <button
      onClick={handleLogout}
      className="bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded"
    >
      Déconnexion
    </button>
  );
};

export default LogoutButton;
