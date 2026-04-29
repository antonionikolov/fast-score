import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    proxy: {
      // Forwards http://localhost:3000/api -> http://localhost:8080/api
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        secure: false,
      }
    }
  },
  build: {
    // This directs the build output to a folder Maven can easily grab
    outDir: 'dist',
    emptyOutDir: true,
  }
})