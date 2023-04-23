import React from "react";
import {Link} from "react-router-dom";

const header = (props) => {
    return (
        <header>
            <nav className="navbar navbar-expand-md navbar-dark bg-primary">
                <div className="container">
                    <a className="navbar-brand" href="/">Book Store</a>
                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarsExampleDefault"
                            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                        <ul className="navbar-nav m-auto">
                            <li className="nav-item m-auto">
                                <Link className="nav-link active" to={"/books"}>Books</Link>
                            </li>
                            <li className="nav-item m-auto">
                                <Link className="nav-link active" to={"/categories"}>Categories</Link>
                            </li>
                        </ul>
                        <form className="form-inline mt-2 mt-md-0 ml-3">
                            <Link className="btn btn-outline-light my-2 my-sm-0" to={"/login"}>Login</Link>
                        </form>
                    </div>
                </div>
            </nav>
        </header>

    )
}

export default header;