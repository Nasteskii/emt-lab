import axios from "axios";

const instance = axios.create({
    baseUrl: "localhost:9090/api",
    headers: {
        'Access-Control-Allow-Origin' : '*',
    }
})

export default instance;