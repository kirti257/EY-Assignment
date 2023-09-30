import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { API_URL } from '../constant';


function EmployeeSearch() {
    const [letter, setLetter] = useState('');
    const [results, setResults] = useState([]);

    const handleSearch = () => {
        axios.get(`${API_URL}?letter=${letter}`)
            .then(response => {
                // console.log(response);
                console.log(response.data);
                setResults(response.data);
            })
            .catch(error => {
                console.error('Error searching employees:', error);
            });
    };

    const handleInputChange = (e) => {
        setLetter(e.target.value);
    };

    useEffect(() => {
        if (letter) {
            handleSearch();
        } else {
            setResults([]); 
        }
    }, [letter]);

    return (
        <div className="employee-search-card"> 
            <input
                type="text"
                placeholder="Enter City"
                value={letter}
                onChange={handleInputChange}
                className="employee-search-input form-control"
            />
          
            <ul className="employee-list">
                {results.map(employee => (
                    <li key={employee.id} className="employee-list-item">
                        {employee.address} - {employee.name}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default EmployeeSearch;
