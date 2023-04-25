import React from "react";
import {Link, useNavigate} from "react-router-dom";

const BookEdit = (props) => {
    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "NOVEL",
        authors: [1],
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
        const name = formData.name !== "" ? formData.name : props.book.name;
        const category = formData.category !== 0 ? formData.category : props.book.category;
        const authors = formData.authors !== [] ? formData.authors : props.book.authors;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;

        props.onEditBook(props.book.id, name, category, authors, availableCopies);
        navigate("/books");
    }

    return (
        <div className="row">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <input id="id"  name="id" value={props.book.id}/>
                    <div className="form-group">
                        <label htmlFor="name">Name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder={props.book.name}
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
                            {props.categories.map((term) => {
                                if (props.book.category !== undefined &&
                                    props.book.category === term)
                                    return <option selected={props.book.category} value={term}>{term}</option>
                                else return <option value={term}>{term}</option>
                            })}
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
                            {props.authors.map((term) => {
                                if (props.book.authors !== undefined &&
                                    props.book.authors[0] === term.id)
                                    return <option selected={props.book.authors.find((r) => r.id === term.id)}
                                                   value={term.id}>{term.name}</option>
                                else return <option value={term.id}>{term.name}</option>
                            })}
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

export default BookEdit;