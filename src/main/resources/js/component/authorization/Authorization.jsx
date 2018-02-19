import React from "react";
import { Link } from 'react-router-dom'

export default class Authorization extends React.Component{
    render() {
        return (
            <div align="center"  className='container'>
                <ul>
                    <li><Link to='/login'>Log In</Link></li>
                    <li><Link to='/registration'>Registration</Link></li>
                </ul>

            </div>
        )
    }
}