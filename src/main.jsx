import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
// import App from './App.jsx'
import ScientistList from './mission0205/ScientistList.jsx'

// createRoot(document.getElementById('root')).render(
//   <StrictMode>
//     <App />
//   </StrictMode>
// )


createRoot(document.getElementById('root')).render(
  <StrictMode>
    <ScientistList />
  </StrictMode>
)