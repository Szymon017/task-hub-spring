import './App.css';
import AddNewProject from './components/AddNewProject';
import HomePage from './components/HomePage';
import NavBar from './components/NavBar';
import { BrowserRouter, Route, Routes } from "react-router-dom"
function App() {
  return (
    <>
      <header>
        <NavBar />
      </header>
      <div className="App">
        <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />}/>
          <Route path="addNew" element={<AddNewProject/>}/>
        </Routes>
        </BrowserRouter>
      </div>
    </>
  );
}

export default App;
