import React from "react";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material";

const compatibilityMatrix = {
  Planner: {
    Planner: 95,
    Saver: 85,
    "Risk Taker": 60,
    Investor: 70,
    "Security Seeker": 80,
    "Spontaneous Spender": 50,
  },
  Saver: {
    Planner: 85,
    Saver: 95,
    "Risk Taker": 55,
    Investor: 65,
    "Security Seeker": 90,
    "Spontaneous Spender": 45,
  },
  "Risk Taker": {
    Planner: 60,
    Saver: 55,
    "Risk Taker": 95,
    Investor: 90,
    "Security Seeker": 40,
    "Spontaneous Spender": 70,
  },
  Investor: {
    Planner: 70,
    Saver: 65,
    "Risk Taker": 90,
    Investor: 95,
    "Security Seeker": 60,
    "Spontaneous Spender": 75,
  },
  "Security Seeker": {
    Planner: 80,
    Saver: 90,
    "Risk Taker": 40,
    Investor: 60,
    "Security Seeker": 95,
    "Spontaneous Spender": 35,
  },
  "Spontaneous Spender": {
    Planner: 50,
    Saver: 45,
    "Risk Taker": 70,
    Investor: 75,
    "Security Seeker": 35,
    "Spontaneous Spender": 95,
  },
};

const types = Object.keys(compatibilityMatrix);

const getCellColor = (score) => {
  if (score >= 80) return "#34D399"; 
  if (score >= 60) return "#FBBF24"; 
  return "#F87171"; 
};

const cellStyle = {
  width: 80,
  height: 50,
  textAlign: "center",
  verticalAlign: "middle",
  fontWeight: "bold",
  color: "#fff",
  padding: 0,
};

const CompatibilityGrid = () => {
  return (
    <TableContainer component={Paper} style={{ marginTop: 20 }}>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell style={{ ...cellStyle, backgroundColor: "#f3f4f6", color: "#000" }}>Type</TableCell>
            {types.map((colType) => (
              <TableCell
                key={colType}
                align="center"
                style={{ ...cellStyle, backgroundColor: "#f3f4f6", color: "#000" }}
              >
                {colType}
              </TableCell>
            ))}
          </TableRow>
        </TableHead>
        <TableBody>
          {types.map((rowType) => (
            <TableRow key={rowType}>
              <TableCell style={{ ...cellStyle, backgroundColor: "#f3f4f6", color: "#000" }}>
                {rowType}
              </TableCell>
              {types.map((colType) => {
                const score = compatibilityMatrix[rowType][colType];
                return (
                  <TableCell
                    key={colType}
                    style={{
                      ...cellStyle,
                      backgroundColor: getCellColor(score),
                    }}
                  >
                    {score}
                  </TableCell>
                );
              })}
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default CompatibilityGrid;
