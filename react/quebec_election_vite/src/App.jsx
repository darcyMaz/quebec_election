import { useState } from 'react'
import Hex01 from './Hex01.jsx'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>

      <div>
        <Hex01 />
        <Hex01 />
      </div>
    </>
  )
}

export default App
