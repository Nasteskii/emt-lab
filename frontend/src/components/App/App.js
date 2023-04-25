import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import Books from "../Books/BookList/books";
import LibraryService from "../repository/LibraryRepository";
import Categories from "../Categories/categories";
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/bookAdd"
import Authors from "../Authors/authors";
import BookEdit from "../Books/BookEdit/bookEdit";
import data from "bootstrap/js/src/dom/data";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: [],
            authors: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                            <Route path={"/books/add"} exact
                                   element={<BookAdd categories={this.state.categories} authors={this.state.authors}
                                                     onAddBook={this.addBook}/>}/>
                            <Route path={"/books/edit/:id"} exact element={<BookEdit categories={this.state.categories}
                                                                                     authors={this.state.authors}
                                                                                     onEditBook={this.editBook}
                                                                                     book={this.state.selectedBook}/>}/>
                            <Route path={"/books"} exact
                                   element={<Books books={this.state.books} onDelete={this.deleteBook}
                                                   onEdit={this.getBook} onTake={this.takeBook}/>}/>
                            <Route path={"/categories"} exact
                                   element={<Categories categories={this.state.categories}/>}/>
                            <Route path={"/authors"} exact
                                   element={<Authors authors={this.state.authors}/>}/>
                            <Route path={"/"} exact
                                   element={<Books books={this.state.books} onDelete={this.deleteBook}
                                                   onEdit={this.getBook} onTake={this.takeBook}/>}/>
                        </Routes>
                        {/*<Navigate to={"/books"}/>*/}
                    </div>
                </main>
            </Router>
        )
    }

    componentDidMount() {
        this.loadBooks();
        this.loadCategories();
        this.loadAuthors();
    }

    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                });
            });
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                });
            });
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                });
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, category, authors, availableCopies) => {
        LibraryService.addBook(name, category, authors, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    editBook = (id, name, category, authors, availableCopies) => {
        LibraryService.editBook(id, name, category, authors, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }
    takeBook = (id) => {
        LibraryService.takeBook(id)
            .then(() => {
                this.loadBooks();
            });
    }
}

export default App;
