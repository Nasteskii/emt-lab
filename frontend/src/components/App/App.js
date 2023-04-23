import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import Books from "../Books/BookList/books";
import LibraryService from "../repository/LibraryRepository";
import Categories from "../Categories/categories";
import Header from "../Header/header";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: []
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                            <Route path={"/"} exact element={<Books books={this.state.books}/>}/>
                            <Route path={"/books"} exact element={<Books books={this.state.books}/>}/>
                            <Route path={"/categories"} exact
                                   element={<Categories categories={this.state.categories}/>}/>
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
    }

    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }
}

export default App;
