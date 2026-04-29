import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Trophy } from 'lucide-react';

function App() {
  const [tournaments, setTournaments] = useState([]);

  useEffect(() => {
    // This calls your Spring Boot Controller!
    axios.get('/api/v1/tournaments')
      .then(res => setTournaments(res.data))
      .catch(err => console.error("API Error:", err));
  }, []);

  return (
    <div style={{ padding: '2rem', fontFamily: 'sans-serif' }}>
      <h1><Trophy size={32} /> Tournament Manager</h1>
      <ul>
        {tournaments.map((t: any) => (
          <li key={t.id}>{t.name} - {t.type}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;