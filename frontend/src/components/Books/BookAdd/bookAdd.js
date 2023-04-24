import React from "react";
import {Link, useNavigate} from "react-router-dom";

const BookAdd = (props) => {
    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "NOVEL",
        authors: 1,
        availableCopies: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const category = formData.category;
        const authors = [formData.authors];
        const availableCopies = formData.availableCopies;

        props.onAddBook(name, category, authors, availableCopies);
        history("/books")
    }
    return (
        <div className="row">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                        <div className="form-group">
                            <label htmlFor="name">Name</label>
                            <input type="text"
                                   className="form-control"
                                   id="name"
                                   name="name"
                                   required
                                   placeholder="Enter book name"
                                   onChange={handleChange}
                            />
                        </div>
                        <div className="form-group">
                            <label>Category</label>
                            <select className="form-control"
                                    id="category"
                                    name="category"
                                    onChange={handleChange}
                            >
                                {props.categories.map((term) =>
                                    <option value={term}>{term}</option>
                                )}
                            </select>
                        </div>
                        <div className="form-group">
                            <label>Authors</label>
                            <br/>
                            <select className="form-control"
                                    id="authors"
                                    name="authors"
                                    onChange={handleChange}
                            >
                                {props.authors.map((term) =>
                                    <option value={term.id}>{term.name}</option>
                                )}
                            </select>
                        </div>
                        <div className="form-group">
                            <label>Available Copies</label>
                            <input type="number"
                                   id="availableCopies"
                                   name="availableCopies"
                                   onChange={handleChange}
                            />
                        </div>
                        <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                        <Link type="button" className="btn btn-primary" to={"/books"}>Back</Link>
                </form>
            </div>
        </div>
    )
}

export default BookAdd;