import React, { useEffect, useState } from "react";
import axios from "axios";
import { BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid, ResponsiveContainer } from "recharts";

const PersonalityTypeChart = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        axios.get("https://personafi.onrender.com")

            .then(response => {
                // Convert Map<String, Long> to array for Recharts
                const chartData = Object.entries(response.data).map(([type, count]) => ({
                    personalityType: type,
                    count: count
                }));
                setData(chartData);
            })
            .catch(error => {
                console.error("Error fetching personality type counts:", error);
            });
    }, []);

    return (
        <div style={{ width: "100%", height: 300 }}>
            <ResponsiveContainer>
                <BarChart data={data}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="personalityType" />
                    <YAxis allowDecimals={false} />
                    <Tooltip />
                    <Bar dataKey="count" fill="#1E3A8A"/>
                </BarChart>
            </ResponsiveContainer>
        </div>
    );
};

export default PersonalityTypeChart;
