import './index.css'

const FrontPage = () => {
    return ( 
        <>

            {/* So its gotta have two divs. Ones for the quebec map and one for the info side.  */}
            <div className="quebec-div">  

                {/* In here will be a big map of quebec, a bit opaque.
                    Inside of this big map will be all of the regions.
                    So it won't be all 17 regions, it'll be custom regions.
                */}

                {/* Background Image */}
                <img
                    src="/quebec-map-1.png"
                    alt="Background"
                    className="quebec-map"
                />

                {/* Small Image 1 */}
                { /* I can copy paste to make a second image but it needs
                     to be set to a different position. */ }
                <img
                    src="/cat-pic-1.jpg"
                    alt="Overlay 1"
                    className="quebec-region"
                />

            </div>
            
            
        </>
     );
}
 
export default FrontPage;