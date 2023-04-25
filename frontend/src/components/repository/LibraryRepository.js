import axios from "../custom-axios/axios";

const LibraryService = {
    fetchBooks: () => {
        return axios.get("books");
    },
    fetchCategories: () => {
        return axios.get("categories");
    },
    fetchAuthors: () => {
        return axios.get("authors");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name, category, authors, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "category": category,
            "authors": authors,
            "availableCopies": availableCopies
        })
    },
    editBook: (id, name, category, authors, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "id": id,
            "name": name,
            "category": category,
            "authors": authors,
            "availableCopies": availableCopies
        })
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    takeBook: (id) => {
        return axios.get(`/books/get-book/${id}`);
    }

}

export default LibraryService;