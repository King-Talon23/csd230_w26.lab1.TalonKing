import { useState } from 'react'
import BookList from './BookList'
import AddBookForm from './AddBookForm'

function App() {
  const [refreshKey, setRefreshKey] = useState(0);

  const handleBookAdded = () => {
    setRefreshKey(prev => prev + 1);
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1>My Bookstore Frontend</h1>
      <AddBookForm onBookAdded={handleBookAdded} />
      <hr />
      <BookList key={refreshKey} />
    </div>
  )
}

export default App
