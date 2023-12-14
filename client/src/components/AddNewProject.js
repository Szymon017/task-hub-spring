import React, { useState } from 'react'
import { addNewProject } from '../services/ProjectService';
import { addTask } from '../services/TaskService';

function AddNewProject() {

    const [project, setProject] = useState({});

    const onChangeFunc = (e) => {
        setProject({
            ...project,
            [e.target.name]: e.target.value
        })


    }

    const handleClick = (e) => {
        e.preventDefault();
        addNewProject(project);
        window.location.href = "http://localhost:3000/";    
    }

    return (
        <div className='mainContainer'>
            <div className='addProjectContainer'>
                <h1>Add new project</h1>
                <form>
                    <p>Project name</p>
                    <input type="text" name="name" id="name"  onChange={ onChangeFunc} placeholder='project name...'></input><br/>
                    <button type="submit" onClick={handleClick}>Add</button>
                </form>
            </div>
        </div>
    )
}

export default AddNewProject