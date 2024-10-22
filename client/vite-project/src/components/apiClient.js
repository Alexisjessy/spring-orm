
import axios from 'axios';

export const API_ENDPOINT = 'http://localhost:8000'; 


const apiClient = axios.create({
  baseURL: API_ENDPOINT,
  // withCredentials: true, 
});


apiClient.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) {
    config.Authorization['token'] = token;
  }
  return config;
}, (error) => {
  console.error('Erreur lors de l\'envoi de la requête:', error);
  return Promise.reject(error);
});
export default apiClient;
