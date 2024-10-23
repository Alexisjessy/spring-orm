import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default {
  server: {
    proxy: {
      '/auth': {
        target: 'http://localhost:8000',  
        changeOrigin: true,
        secure: false,
      },
    },
  },
};
