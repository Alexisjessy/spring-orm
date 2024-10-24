import axios from 'axios';

export const API_ENDPOINT = 'http://localhost:8000';


const apiClient = axios.create({
  baseURL: API_ENDPOINT,
  headers: {
    'Content-Type': 'application/json',
  },
});


apiClient.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) {
    console.log('Token trouvé dans localStorage:', token);
    config.headers['Authorization'] = `Bearer ${token}`;
  } else {
    console.warn('Aucun token trouvé dans localStorage.');
  }
  return config;
}, error => {
  console.error('Erreur lors de l\'envoi de la requête:', error);
  return Promise.reject(error);
});


// apiClient.interceptors.response.use(
//   response => response,
//   error => {
//     if (error.response) {
//       const { status } = error.response;


//       if (status === 401) {
//         console.error('Token invalide ou expiré. Redirection vers la page de login.');
//         localStorage.removeItem('token');
//         window.location.href = '/login';
//       }
//     }
//     return Promise.reject(error);
//   }
// );


export async function getRequest(URL) {
  try {
    const response = await apiClient.get(URL);
    return response.data;
  } catch (error) {
    console.error('Erreur lors de la requête GET:', error);
    throw error;
  }
}


export async function postRequest(URL, data) {
  try {
    const response = await apiClient.post(URL, data);
    return response.data;
  } catch (error) {
    console.error('Erreur lors de la requête POST:', error);
    throw error;
  }
}

export default apiClient;
