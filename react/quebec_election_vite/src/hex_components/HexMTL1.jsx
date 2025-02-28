import { HexGrid, Layout, Hexagon, Text, Pattern, Path, Hex } from 'react-hexgrid';
import './hex.css'

const HexMTL1 = () => {
    return ( 
        
        <>
            {/*  Instead of having HexMTL I should do HexPage generator which
                 and hexPages.json which generates a hex page as you open it. */}

            <div>
                {/* This will be a header saying it is the montreal region */}
                <h2> Montréal Electoral Region Results </h2>
            </div>

            <HexGrid className="hexgrid" width={460} height={340} viewBox="-35 -30 60 60" >
            <Layout size={{ x: 4, y: 4 }} flat={true} spacing={1} origin={{ x: 0, y: 0 }}>
            
            <Hexagon q={0} r={0} s={0} >
                <Text className="hextext"> 35 </Text>
            </Hexagon>
            <Hexagon q={0} r={-1} s={1} >
                <Text className="hextext"> 33 </Text>
            </Hexagon>
            <Hexagon q={0} r={-2} s={2} >
                <Text className="hextext"> 31.1 </Text>
            </Hexagon>
            <Hexagon q={0} r={1} s={-1} >
                <Text className="hextext"> 36 </Text>
            </Hexagon>
            <Hexagon q={0} r={2} s={-2} >
                <Text className="hextext"> 42.2 </Text>
            </Hexagon>
            
            <Hexagon q={1} r={-1} s={0} >
                <Text className="hextext"> 29 </Text>
            </Hexagon>
            <Hexagon q={2} r={-2} s={0} >
                <Text className="hextext"> 26 </Text>
            </Hexagon>
            <Hexagon q={3} r={-4} s={1} >
                <Text className="hextext"> 25.2 </Text>
            </Hexagon>
            <Hexagon q={-1} r={1} s={0} >
                <Text className="hextext"> 43.2 </Text>
            </Hexagon>
            <Hexagon q={-2} r={2} s={0} >
                <Text className="hextext"> 44 </Text>
            </Hexagon>
            <Hexagon q={-3} r={3} s={0} >
                <Text className="hextext"> 47.2 </Text>
            </Hexagon>

            <Hexagon q={-1} r={0} s={1} >
                <Text className="hextext"> 34 </Text>
            </Hexagon>
            <Hexagon q={-2} r={0} s={2} >
                <Text className="hextext"> 45.2 </Text>
            </Hexagon>
            <Hexagon q={1} r={0} s={-1} >
                <Text className="hextext"> 28 </Text>
            </Hexagon>

            <Hexagon q={-1} r={-1} s={2} >
                <Text className="hextext"> 32 </Text>
            </Hexagon>
            <Hexagon q={1} r={-2} s={1} >
                <Text className="hextext"> 30 </Text>
            </Hexagon>
            <Hexagon q={2} r={-3} s={1} >
                <Text className="hextext"> 25.1 </Text>
            </Hexagon>
            <Hexagon q={1} r={-3} s={2} >
                <Text className="hextext"> 31.2 </Text>
            </Hexagon>
            <Hexagon q={-1} r={2} s={-1} >
                <Text className="hextext"> 42.1 </Text>
            </Hexagon>

            <Hexagon q={3} r={-3} s={0} >
                <Text className="hextext"> 24.2 </Text>
            </Hexagon>
            <Hexagon q={3} r={-2} s={1} >
                <Text className="hextext"> 24.1 </Text>
            </Hexagon>

            <Hexagon q={2} r={-1} s={-1} >
                <Text className="hextext"> 27 </Text>
            </Hexagon>

            <Hexagon q={1} r={1} s={-2} >
                <Text className="hextext"> 37 </Text>
            </Hexagon>

            <Hexagon q={-2} r={3} s={-1} >
                <Text className="hextext"> 41 </Text>
            </Hexagon>
            <Hexagon q={-1} r={3} s={-2} >
                <Text className="hextext"> 38 </Text>
            </Hexagon>
            <Hexagon q={-1} r={4} s={-3} >
                <Text className="hextext"> 39 </Text>
            </Hexagon>
            <Hexagon q={-2} r={4} s={-2} >
                <Text className="hextext"> 40 </Text>
            </Hexagon>
            <Hexagon q={-3} r={4} s={-1} >
                <Text className="hextext"> 47.3 </Text>
            </Hexagon>

            <Hexagon q={-2} r={-1} s={3} >
                <Text className="hextext"> 45.1 </Text>
            </Hexagon>

            <Hexagon q={-4} r={3} s={1} >
                <Text className="hextext"> 47.1 </Text>
            </Hexagon>
            <Hexagon q={-3} r={2} s={1} >
                <Text className="hextext"> 46.2 </Text>
            </Hexagon>
            <Hexagon q={-2} r={1} s={1} >
                <Text className="hextext"> 43.1 </Text>
            </Hexagon>
            <Hexagon q={-3} r={1} s={2} >
                <Text className="hextext"> 46.1 </Text>
            </Hexagon>
            <Hexagon q={-4} r={2} s={2} >
                <Text className="hextext"> 50 </Text>
            </Hexagon>
            <Hexagon q={-5} r={2} s={3} >
                <Text className="hextext"> 49 </Text>
            </Hexagon>
            <Hexagon q={-5} r={3} s={2} >
                <Text className="hextext"> 48.1 </Text>
            </Hexagon>
            <Hexagon q={-6} r={4} s={2} >
                <Text className="hextext"> 48.2 </Text>
            </Hexagon>
            <Hexagon q={-6} r={3} s={3} >
                <Text className="hextext"> 48.3 </Text>
            </Hexagon>


            </Layout>
            </HexGrid>
        </>
     );
}
 
export default HexMTL1;