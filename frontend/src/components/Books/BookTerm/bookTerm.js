import React from "react";

const bookTerm = (props) => {
    return (
        <tr>
            <td scope={"col"}>{props.term.name}</td>
            <td scope={"col"}>{props.term.category}</td>
            <td scope={"col"}>{props.term.authors.map((term) => {
                return (
                    <li key={term.id}>{term.name}</li>
                );
            })}</td>
            <td scope={"col"}>{props.term.availableCopies}</td>
        </tr>
    )
}

export default bookTerm;