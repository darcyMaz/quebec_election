import { HexGrid, Layout, Hexagon, Text, Pattern, Path, Hex } from 'react-hexgrid';
import './hex.css'

const HexMTL1 = () => {
    return ( 
        <>
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
            <Hexagon q={0} r={1} s={-1} />
            <Hexagon q={0} r={2} s={-2} />
            
            <Hexagon q={1} r={-1} s={0} />
            <Hexagon q={2} r={-2} s={0} />
            <Hexagon q={3} r={-3} s={0} />
            <Hexagon q={-1} r={1} s={0} />
            <Hexagon q={-2} r={2} s={0} />
            <Hexagon q={-3} r={3} s={0} />

            <Hexagon q={-1} r={0} s={1} />
            <Hexagon q={-2} r={0} s={2} />
            <Hexagon q={1} r={0} s={-1} />

            <Hexagon q={-1} r={-1} s={2} >
                <Text className="hextext"> 32 </Text>
            </Hexagon>
            <Hexagon q={1} r={-2} s={1} >
                <Text className="hextext"> 30 </Text>
            </Hexagon>
            <Hexagon q={2} r={-3} s={1} >
                <Text className="hextext"> 25.1 </Text>
            </Hexagon>
            


            </Layout>
            </HexGrid>
        </>
     );
}
 
export default HexMTL1;