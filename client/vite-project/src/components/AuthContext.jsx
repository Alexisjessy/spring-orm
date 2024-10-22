
import React, { createContext, useState, useEffect } from 'react';
import apiClient from './apiClient';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const checkUserAuth = async () => {
      try {
        const userData = await checkAuth(); 
        setUser(userData);
      } catch (error) {
        console.log('Utilisateur non authentifié', error);
      } finally {
        setLoading(false);
      }
    };

    checkUserAuth();
  }, []);

  const login = async (email, password) => {
    try {
      const response = await apiClient.post('/auth/login', { email, password });
      const { token } = response.data;
      if (token) {
        localStorage.setItem('Token', token);
        console.log('Token sauvegardé dans localStorage:', token);
      }
      setUser(response.data.user);
      return response.data;
    } catch (error) {
      console.error('Erreur lors de la connexion', error);
      throw error;
    }
  };

  // Fonction pour vérifier l'authentification
  const checkAuth = async () => {
    try {
      const response = await apiClient.get('/users/me');
      setUser(response.data.user);
      return response.data;
    } catch (error) {
      console.error('Erreur lors de la vérification de l\'authentification', error);
      setUser(null);
      throw error;
    }
  };

  // Fonction pour gérer la déconnexion
  const logout = async () => {
    try {
      await apiClient.post('/auth/logout');
      localStorage.removeItem('token'); 
      setUser(null); 
      console.log('Déconnexion réussie');
    } catch (error) {
      console.error('Erreur lors de la déconnexion', error);
      throw error;
    }
  };

  return (
    <AuthContext.Provider value={{ user, login, logout, loading }}>
      {children}
    </AuthContext.Provider>
  );
};
