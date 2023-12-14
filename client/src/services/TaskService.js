import axios from "axios";

export const getTasks = async (id) =>{
    try{
        return await axios.get(`http://localhost:8080/project/${id}/tasks`)
    } catch( error ) {
        return error;
    }
}

export const addTask = async (id, task) => {
    try {
        return await axios.post(`http://localhost:8080/project/${id}/tasks`, task)
    } catch (error) {
        return error;
    }
}

export const updateTask = async(task) => {
    
    try {
        return await axios.put(`http://localhost:8080/task/${task.id}`, task)
    } catch (error) {
        return error;
    }
}