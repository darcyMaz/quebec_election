import { HexGrid, Layout, Hexagon, Text, Pattern, Path, Hex } from 'react-hexgrid';
import './hex.css'

const Hex01 = () => {
    
    // These line will let you generate hexagaons.
    // This one outside the return call
    // const hexagons = GridGenerator.parallelogram(-2, 3, -2, 1);
    // { hexagons.map((hex, i) => <Hexagon key={i} q={hex.q} r={hex.r} s={hex.s} />) }
    
    return ( 
        <>

            <HexGrid className="hexgrid" width={460} height={340} viewBox="-35 -30 60 60" >
                {/* Grid with manually inserted hexagons */}
                <Layout size={{ x: 4, y: 4 }} flat={true} spacing={1.4} origin={{ x: 0, y: 0 }}>
                <Hexagon q={0} r={0} s={0} />
                {/* Using pattern (defined below) to fill the hexagon */}
                <Hexagon q={0} r={-1} s={1} fill="pat-1" />
                <Hexagon q={0} r={1} s={-1} />
                <Hexagon q={1} r={-1} s={0}> </Hexagon>
                <Hexagon q={1} r={0} s={-1}> </Hexagon>
                <Hexagon q={-1} r={1} s={0} fill="pat-2"> </Hexagon>
                <Hexagon q={-1} r={0} s={1} />
                <Hexagon q={-2} r={0} s={1} />
                {/* <Path start={new Hex(0, 0, 0)} end={new Hex(-2, 0, 1)} /> */}
                </Layout>
                <Pattern id="pat-1" link="/CAQ-Colour.png" />
                <Pattern id="pat-2" link="/Liberal-Colour.png" />
            </HexGrid>

        </>
        );


}
 
export default Hex01;