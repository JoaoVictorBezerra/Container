import axios from "axios"
const url: String = import.meta.env.VITE_API_URL;

const api = axios.create({
    baseURL: `${url}`
})
export default api;