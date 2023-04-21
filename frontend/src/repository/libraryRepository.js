import axios from "../custom-axios/axios";

const LibraryService = {
    fetchBooks: () => {
        return axios.get("/", "/books");
    },
    addBook: (name, category, authors, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "category": category,
            "authors": authors,
            "availableCopies": availableCopies
        });
    },
    editBook: (id, name, category, authors, availableCopies) => {
        return axios.put("/books/edit/${id}", {
            "name": name,
            "category": category,
            "authors": authors,
            "availableCopies": availableCopies
        });
    },
    deleteBook: (id) => {
        return axios.delete("/books/delete/${id}")
    },
    getBook: (id) => {
        return axios.get("/books/get-book/${id}")
    },
    fetchCategories: () => {
        return axios.get("/categories")
    },
    fetchAuthors: () => {
        return axios.get("/authors")
    },
    login: (username, password) => {
        return axios.post("/login", {
            "username": username,
            "password": password
        });
    }
}

export default LibraryService;