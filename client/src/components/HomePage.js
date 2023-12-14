import React, { useEffect, useRef, useState } from 'react'
import AddNewProject from './AddNewProject'
import { getProjects } from '../services/ProjectService';
import { addTask, getTasks, updateTask } from '../services/TaskService';

function HomePage() {
  const [projects, setProjects] = useState([]);
  const [tasks, setTasks] = useState([]);
  const [actualProject, setActualProject] = useState();
  const [taskTrigger, setTaskTrigger] = useState(false);
  const [taskForm, setTaskForm] = useState({});
  const [coveredTask, setCoveredTask] = useState();

  const getAllProjects = async () => {
    const project = await getProjects();
    setProjects(project.data || []);
  }

  const getAllTasks = async (id) => {
    const task = await getTasks(id);
    setTasks(task.data);
  }

  useEffect(() => {
    getAllProjects();
  }, [])

  const handleSubmit = async (e) => {
    e.preventDefault();
    setTaskTrigger(false);
    const result = await addTask(actualProject, taskForm);
    setTasks(prevTasks => [...prevTasks, result.data])
    console.log(result.data);
  }

  const handleChange = (e) => {
    setTaskForm({
      ...taskForm,
      [e.target.name]: e.target.value
    })
  }

  const changeProject = (id) => {
    console.log(id);
    setTaskTrigger(false)
    getAllTasks(id)
    setActualProject(id)
  }

  const handleEnter = (id) => {
    setCoveredTask(id);
    console.log(id);
  }

  const handleExit = () => {
    setCoveredTask(0);
  }

  const handleDoneClick = async (value) => {
    const oldTask = {...value};
    const updateTask_ = tasks.filter((task) => task.id === value.id)[0];
    updateTask_.isDone = true;
    
    if(!oldTask.isDone){
      const result = await updateTask(updateTask_, value.id)
    }
  }

  return (
    <div className='projectContainer'>
      <div style={{ float: "left", boxShadow: "10px 5px 5px 5px rgb(235, 235, 235)", width: "100%" }}>
        <div className='projectsList'>
          <h3 style={{ borderBottom: "2px solid black", textAlign: "center" }}>Actual Projects</h3 >
          {
            projects?.map((value) => (
              <p style={{
                backgroundColor: value.id === actualProject ? "#4caf50" : "transparent",
                color: value.id === actualProject ? "white" : "black"
              }} onClick={() => {
                changeProject(value.id)
              }}>{value.name}</p>
            ))
          }
        </div>
        <div className='projectsTasks'>
          <div className='projectTasksHeader'><h3 style={{ borderBottom: "2px solid black", textAlign: "center" }}>Actual Tasks</h3></div>
          <div className='projectTasksBody'>
            {
              tasks?.map((value) => (
                <div className='tasksList'>
                  <div className='taskDesc'>{value.description}</div>
                  <div className='taskIsDone'
                    style={{ backgroundColor: value.id === coveredTask ? "#4caf50" : "" }}
                    onMouseEnter={() => { handleEnter(value.id) }}
                    onMouseLeave={handleExit}
                    onClick={() => { handleDoneClick(value) }}
                  >
                    {value.isDone ? "Done" : "Not done"}
                  </div>
                </div>
              ))
            }
          </div>
          <div className='projectTasksFooter'>
            {actualProject ? (

              !taskTrigger ? (<button onClick={() => { setTaskTrigger(true) }}>Add task</button>) : (
                <div>
                  <form method='post'>
                    <input type="text" placeholder="task description..." name="description" id="description" onChange={handleChange} />
                    <button onClick={handleSubmit} type='submit'>Add</button>
                  </form>
                </div>
              )

            ) : ""}
          </div>
        </div>
      </div>
    </div>
  )
}

export default HomePage

