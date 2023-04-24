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
    }
}

export default LibraryService;