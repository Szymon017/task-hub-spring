import axios from 'axios';

export const getProjects = async () => {
    try {
        return await axios.get(
            'http://localhost:8080/project'
        )
    } catch (error) {
        return error;
    }
}

export const addNewProject = async(project) => {
    try {
        return await axios.post(
            `http://localhost:8080/project`, project
        )
    } catch (error) {
        return error;
    }
}

