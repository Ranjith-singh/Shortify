import { useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <h1 class="text-5xl font-mono underline">
        Hello world!
      </h1>
    </>
  )
}

export default App
