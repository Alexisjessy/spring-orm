
import axios from 'axios';

export const API_ENDPOINT = 'http://localhost:8000'; 


const apiClient = axios.create({
  baseURL: API_ENDPOINT,
  // withCredentials: true, 
  // crossorigin : true
});


apiClient.interceptors.request.use(config => {
  const token = localStorage.getItem('Token');
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`;
    //  config.headers['Cache-Control'] = 'no-cache';
    // config.headers['Access-Control-Allow-Origin'] = "*";
  }
  return config;
}, (error) => {
  console.error('Erreur lors de l\'envoi de la requête:', error);
  return Promise.reject(error);
});

export default apiClient;
