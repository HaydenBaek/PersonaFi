import { createTheme } from '@mui/material/styles';

const theme = createTheme({
    palette: {
        primary: {
            main: '#F59E0B',   // Amber Yellow
        },
        secondary: {
            main: '#1E3A8A',   // Navy Blue
        },
        background: {
            default: '#F9FAFB',  // Light gray background
        },
        text: {
            primary: '#111827',  // Dark gray/black text
        },
    },
});

export default theme;
